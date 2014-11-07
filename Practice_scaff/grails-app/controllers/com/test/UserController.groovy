package com.test

class UserController {

	def messageSource
	
    def index() { 
		List<Album> albums = Album.getAll()
		log.debug "It is in index " + albums
		def msg = messageSource.getMessage('default.doesnt.match.message',
			null,
			Locale.ITALIAN)
			log.debug "show message " + msg
		model : [albums:albums]
	}
	def register() {
		if(request.method == 'POST') {
			def u = new User()
			u.properties['login', 'password', 'firstName', 'lastName'] = params
			if(u.password != params.confirm) {
			u.errors.rejectValue("password", "user.password.dontmatch")
			return [user:u]
			} else if(u.save()) {
			session.user = u
			redirect controller:"store"
			} else {
			return [user:u]
			}
			}
			
	}
	
}
