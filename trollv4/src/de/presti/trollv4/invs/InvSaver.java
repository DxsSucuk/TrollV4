package de.presti.trollv4.invs;

import org.bukkit.inventory.ItemStack;

/*
*	Urheberrechtshinweis														*
*																				*
*	Copyright © Baris Arslan 2019											    *
*	Erstellt: 02.10.2020 / 16:44:59											    *
*																				*
*	Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.			*
*	Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,	*
*	bei Baris Arslan. Alle Rechte vorbehalten.								    *
*																				*
*	Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,			*
*	öffentlichen Zugänglichmachung oder anderer Nutzung							*
*	bedarf der ausdrücklichen, schriftlichen Zustimmung von Baris Arslan	    *
*/
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
