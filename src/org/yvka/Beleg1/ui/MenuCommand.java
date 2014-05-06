package org.yvka.Beleg1.ui;

import org.yvka.Beleg1.ui.menues.HelpMenu;
import org.yvka.Beleg1.utils.StringUtil;


public abstract class MenuCommand extends ApplicationCommand {
	
	protected final class Param {
		protected String name;
		protected String desc;
		protected boolean required;
		
		
		public Param(String name, String desc) {
			this(name, desc, false);
		}
		
		public Param(String name, String desc, boolean required) {
			super();
			this.name = name;
			this.desc = desc;
			this.required = required;
		}
	
		public String getName() {
			return name;
		}
		public String getDesc() {
			return desc;
		}
		public boolean isRequired() {
			return required;
		}
		
		
		@Override
		public String toString() {
			return String.format("%s%-10s %s\n", HelpMenu.INDENT, getName(), getDesc());
		}
		
		public String toStringOnlyName() {
			String name = "<" + getName() + ">";
			if(!isRequired()) {
				name = "[" + name + "]";
			}
			return name;
		}
		
	}
	
	
	public MenuCommand(Application app) {
		super(app);
	}

	public abstract String getName();
	public abstract String getDescription();
	public abstract String getHelp();
	
	protected String generateHelpTemplate(Param...params) {
		StringBuilder str = new StringBuilder(); 
		str.append(getDescription() + StringUtil.LINE_SEPERATOR);
		str.append(StringUtil.LINE_SEPERATOR);
		str.append("Usage: matrix " + getName() + " ");
		for(Param param : params) {
			str.append(param.toStringOnlyName());
			str.append(" ");
		}
		str.append(StringUtil.LINE_SEPERATOR);
		str.append(StringUtil.LINE_SEPERATOR);
		if(params.length > 0) {
			str.append("The " +  getName() + "commando has the following parameters:");
			for(Param param : params) {
				str.append(StringUtil.LINE_SEPERATOR);
				str.append(String.format("%s%-10s %s\n", HelpMenu.INDENT, param.getName(), param.getDesc()));
				
			}
		} else {
			 str.append("The " + getName() + "  commando has no parameter.\n");
		}
		return str.toString();
	}
	
}