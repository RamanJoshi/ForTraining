package com.test



import static org.springframework.http.HttpStatus.*
import grails.gorm.DetachedCriteria
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AlbumController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
		log.debug "it is in index " + params
		params.max = Math.min(max ?: 10, 100)
		log.debug "add params max " + params
		respond Album.list(params), model:[albumInstanceCount: Album.count()]
	}

	def show(Album albumInstance) {
		respond albumInstance
	}

	def create() {
		respond new Album(params)
	}

	@Transactional
	def save(Album albumInstance) {
		if (albumInstance == null) {
			notFound()
			return
		}

		if (albumInstance.hasErrors()) {
			respond albumInstance.errors, view:'create'
			return
		}

		albumInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'album.label', default: 'Album'), albumInstance.id])
				redirect albumInstance
			}
			'*' { respond albumInstance, [status: CREATED] }
		}
	}

	def edit(Album albumInstance) {
		respond albumInstance
	}

	@Transactional
	def update(Album albumInstance) {
		if (albumInstance == null) {
			notFound()
			return
		}

		if (albumInstance.hasErrors()) {
			respond albumInstance.errors, view:'edit'
			return
		}

		albumInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'Album.label', default: 'Album'), albumInstance.id])
				redirect albumInstance
			}
			'*'{ respond albumInstance, [status: OK] }
		}
	}

	@Transactional
	def delete(Album albumInstance) {

		if (albumInstance == null) {
			notFound()
			return
		}

		albumInstance.delete flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'Album.label', default: 'Album'), albumInstance.id])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'album.label', default: 'Album'), params.id])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}

	def display(){
		def criteria =Album.createCriteria()
		def query = new DetachedCriteria(Album)

		def newQuery = query.build {
			like('name', '%Ram%')
		}

		def count = criteria.get {
			projections {
				countDistinct('name')
			}
			songs {
				ilike('title', '%banaya%')
			}
		}
		//log.debug "this is 1 " + criteria.count(results)
		log.debug "results is " + count
		log.debug "New Query is " + newQuery
		def rockAlbums = new DetachedCriteria(Album).build {
			eq('name', 'Raman')
		}
		
		log.debug " rock Albums " + rockAlbums
		def list = albums.findAll { it.name == "Raman" }
		log.debug "it is list " + list
		render "showing" 

	}
}
