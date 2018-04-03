package hooks.accessors;

import hooks.Hooks;
import hooks.model.RSClass;

public class ClanMemberManager extends RSClass{
	
	public ClanMemberManager(Object reference) {
		this.reference = reference;
		if (Hooks.clanMemberManager!=null) {
			this.fields = Hooks.clanMemberManager.fields;
			this.name = Hooks.clanMemberManager.name;
			this.obfuscatedName = Hooks.clanMemberManager.obfuscatedName;
		}
	}
	
	public NameableContainer asNameableContainer() {
		return new NameableContainer(this.reference);
	}

}
