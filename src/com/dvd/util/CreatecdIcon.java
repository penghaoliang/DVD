package com.dvd.util;

import java.net.URL;

import javax.swing.ImageIcon;

import com.dvd.DVDshop;


public class CreatecdIcon {
	public static ImageIcon add(String ImageName){
		URL IconUrl = DVDshop.class.getResource("/"+ImageName);
		ImageIcon icon=new ImageIcon(IconUrl);
		return icon;
	}
}
