package com.test

class Song {

	String title
	static belongsTo = Album
	static hasMany = [albums : Album]
    static constraints = {
    }
}
