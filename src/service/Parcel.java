package service;
//package main.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.annotations.Expose;

public class Parcel {
	  @Expose(serialize = false)
	private int length;
	  @Expose(serialize = false)
	private int height;
	  @Expose(serialize = false)
	private int depth;
	private Parcelsize size;
	
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	
	
	public Parcelsize getSize() {
		return size;
	}
	public void setSize(Parcelsize size) {
		this.size = size;
	}
	public int getGirth(){
		List<Integer> dim = sortParcelSides();
		
		int girth = 2*dim.get(0)+2*dim.get(1)+dim.get(2);
		return girth;
	}
	
	public int getLongestSide(){
		List<Integer>dim= sortParcelSides();
		return dim.get(dim.size()-1);
	}
	
	public int getShortestSide(){
		List<Integer> dim = sortParcelSides();
		return dim.get(0);
	}
	
	private List<Integer> sortParcelSides(){
		List<Integer> dim = new ArrayList<Integer>();
		dim.add(length);
		dim.add(height);
		dim.add(depth);
		
		Collections.sort(dim);
		return dim;
	}
	
}
