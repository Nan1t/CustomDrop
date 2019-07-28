package ru.nanit.cdrop.data;

import org.bukkit.Material;

public class Drop {

	private String permission;
	private byte chance;
	private int countMin;
	private int countMax;
	private Material material;
	
	public Drop(Material material, String permission, byte chance, int countMin, int countMax) {
		this.permission = permission;
		this.chance = chance;
		this.countMin = countMin;
		this.countMax = countMax;
		this.material = material;
	}
	
	public String getPermission() {
		return this.permission;
	}
	
	public byte getChance() {
		return chance;
	}
	
	public int getMinCount() {
		return this.countMin;
	}
	
	public int getMaxCount() {
		return this.countMax;
	}
	
	public Material getMaterial() {
		return this.material;
	}
}
