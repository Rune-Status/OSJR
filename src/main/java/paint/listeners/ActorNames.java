package paint.listeners;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import game.LoaderWindow;
import game.OSRSLauncher;
import game.Settings;
import hooks.Hooks;
import hooks.accessors.Client;
import hooks.accessors.Nameable;
import hooks.accessors.Npc;
import hooks.accessors.Player;
import hooks.helpers.LocalPoint;
import hooks.helpers.Perspective;
import hooks.helpers.Point;

public class ActorNames implements PaintListener {
	
	Color purple = new Color(102, 0, 153);

	RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,
			RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

	public ActorNames(Client game) {

	}

	@Override
	public void onRepaint(Graphics g) {
		if (Hooks.client != null)
			if (Hooks.client.isLoggedIn()) {
				if (Settings.SHOW_PLAYER_NAMES) {
						g.setColor(Color.green);
						Player[] ps = Hooks.client.getCachedPlayers();
						for (Player pl : ps) {
							if (pl != null) {
								if (pl.isInClanChat()) {
									Image rank = getRankImage(pl.getClanRank());
									g.setColor(Color.orange);
									String name = pl.getNames().getOriginalName();
									Point p = Perspective.getCanvasTextLocation(Hooks.client, (Graphics2D) g,
											new LocalPoint(pl.asActor().getX(), pl.asActor().getY()), name, 200);
									if (p != null) {
										g.drawString(name, p.getX(), p.getY());
										if (rank != null) {
											Graphics2D g2 = (Graphics2D) g;
											g2.setRenderingHints(this.rh);
											g2.drawImage(rank, p.getX() - 12, p.getY() - 9, LoaderWindow.game);
										}
										g.setColor(Color.green);
									}
								} else {
									String name = pl.getNames().getOriginalName();
									Point p = Perspective.getCanvasTextLocation(Hooks.client, (Graphics2D) g,
											new LocalPoint(pl.asActor().getX(), pl.asActor().getY()), name, 200);
									if (p != null) {
										g.drawString(name, p.getX(), p.getY());
									}
								}

								// g2.drawImage(rank, null, p.getX(), p.getY());
							}
						}
					}

					g.setColor(Color.cyan);
					Npc[] ns = Hooks.client.getCachedNpcs();
					if (ns != null) {
						for (Npc pl : ns) {
							if (pl != null) {
								String name = pl.getNpcComposition().getName();
								if (name != null) {
									if (name.contains("Fishing")) { //$NON-NLS-1$
										String[] actions = pl.getNpcComposition().getActions();
										int i = 50;
										for (String s : actions) {
											if (s != null) {
												Point p1 = Perspective.getCanvasTextLocation(Hooks.client,
														(Graphics2D) g,
														new LocalPoint(pl.asActor().getX(), pl.asActor().getY()), s, i);
												if (p1 != null && name != null && name.compareTo("null") != 0) //$NON-NLS-1$
													g.drawString(s, p1.getX(), p1.getY());
												i -= 50;
											}
										}
									} else {
										Point p = Perspective.getCanvasTextLocation(Hooks.client, (Graphics2D) g,
												new LocalPoint(pl.asActor().getX(), pl.asActor().getY()), name, 190);
										if (p != null && name != null && name.compareTo("null") != 0) //$NON-NLS-1$
											g.drawString(name, p.getX(), p.getY());
									}
								}
							}
						}
					}
				}
			}

	
	
	@SuppressWarnings("resource")
	public Image getRankImage(int clanRank) {
		String imageName = "";
		switch (clanRank) {
		case -1:
			return null;
		case 0:
			imageName = "Friend";
			break;
		case 1:
			imageName = "Recruit";
			break;
		case 2:
			imageName = "Corporal";
			break;
		case 3:
			imageName = "Sergeant";
			break;
		case 4:
			imageName = "Lieutenant";
			break;
		case 5:
			imageName = "Captain";
			break;
		case 6:
			imageName = "General";
			break;
		case 7:
			imageName = "Owner";
			break;
		}
		InputStream is = null;
		try {
			is = new FileInputStream("./resources/clan_ranks/"+imageName+".png");
			Image rank = ImageIO.read(is);
			is.close();
			if (rank!=null)
				return rank;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
