package com.test


class SongController {

	def ApplicationHttpRequest
    def index() {		
	}
	def show() {
		
		log.debug "it in show " + params
		System.println "it is show"
	    def songname = params.song
		log.info("Song Name: ${songname} ")
		/*
		def file = request.getFile('myFile')
		log.debug " file is " + file.size 
		//log.debug " ${file.name} "+${file.name}
		if(file && !file.empty && file.size < 3213840) {
		file.transferTo( new File( "/home/oodles/work/${file.name}" ) )
		}*/
		log.info("Song Name: ${songname} ")
		def song = new Song(title : "${songname}")
		def albumname = params.album
		def albumname2 = params.album2
		log.info("Album Name: ${albumname} ")
		Album album = Album.findByName("${albumname}")
		log.debug "Album is this  " + album
		log.debug "it is " + album.addToSongs(song)
		Album album2 = Album.findByName("${albumname2}")
		log.debug "it is in " + song.addToAlbums(album2)
//		User user = new User(login: "login", password: "password", firstName: "firstName", lastName: "lastName")
//		user.save()
		album.save(flush: true)
		song.save(flush: true)
		log.debug "song is " + album.songs
		render "The time is ${new Date()}"

	}
	def beforeInterceptor = {
		log.debug("Executing action $actionName with params $params")
		}
		
}
