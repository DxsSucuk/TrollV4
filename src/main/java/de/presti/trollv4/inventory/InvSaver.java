package de.presti.trollv4.inventory;

import org.bukkit.inventory.ItemStack;

public class InvSaver {

	String owner;
	ItemStack[] content;
	ItemStack[] extracont;
	ItemStack[] armor;

	public InvSaver(String o, ItemStack[] c, ItemStack[] e, ItemStack[] a) {
		owner = o;
		content = c;
		extracont = e;
		armor = a;
	}
	
	public InvSaver(String o, ItemStack[] c, ItemStack[] a) {
		owner = o;
		content = c;
		armor = a;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public ItemStack[] getContent() {
		return content;
	}

	public void setContent(ItemStack[] content) {
		this.content = content;
	}

	public ItemStack[] getExtracont() {
		return extracont;
	}

	public void setExtracont(ItemStack[] extracont) {
		this.extracont = extracont;
	}

	public ItemStack[] getArmor() {
		return armor;
	}

	public void setArmor(ItemStack[] armor) {
		this.armor = armor;
	}

}