package com.javaoo.store;

import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class Artist {
	private String name;
  SortedSet<String> memberNames = new TreeSet<>();
  Map<String, SortedSet<String>> memberInstruments = new TreeMap<>();

	public Artist(String name) {
    //this(); - not sure why the lab said this was the way to instantiate the new variables even if it was called. Not sure 
    //why it was saying this?
		setName(name);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

  //New Additions

  public Artist(SortedSet<String> memberNames, Map<String, SortedSet<String>> memberInstruments) {
    this.memberNames = memberNames;
    this.memberInstruments = memberInstruments;
  }

  public void addMember(String name, SortedSet<String> instruments) {
    memberNames.add(name);
    memberInstruments.put(name, instruments);
  }

  public SortedSet<String> getMembers() {
    return memberNames;
  }

  public SortedSet<String> getInstruments(String name) {
    return memberInstruments.get(name);
  }

 
}
