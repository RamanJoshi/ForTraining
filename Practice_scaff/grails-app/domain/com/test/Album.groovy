package com.test

class Album {
	
	String name
	
	static hasMany = [songs : Song]
    static constraints = {
    }
}
