package com.rethink.analytics;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseProgressController {
	/*variables for accessign state*/
	Session thisSession = null;
	DateTime currentDateTime = null;
	DateTime interactionDateTime = null;
	
	private static final int SESSION_TIMEOUT = 10;
	
	@Autowired
	private CourseProgressRepository courseProgressRepository;
	
	@Autowired
	private SessionRepository sessionRepository;
	
	@RequestMapping(value = "/get-user-progress", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Session> getUserSessions(  
			String[] userIds,
			long fromDateTime
	) {
		List<Session> userSession;
		List<Session> output = new ArrayList<Session>();
		for (String userId : userIds ) {
			//userSession = sessionRepository.findByUserIdAndEndDateTimeGreaterThan(userId, new DateTime(fromDateTime));
			userSession = sessionRepository.findByUserId(userId);
			for ( Session session : userSession ) {
				output.add( session );
			}
		}
		return output;
    }
	
	/*
	 * Build batch view Session once off
	 */
	@RequestMapping(value = "/create-session-and-date-batch-view", method = RequestMethod.GET )
	public void createSessionAndDateBatchView( ) {
		List<Session> sessions = new ArrayList();
		thisSession = null;
		sessionRepository.deleteAll();
		
		courseProgressRepository.findAll()
								.stream()
								.forEach( courseProgress 
			-> { 
					currentDateTime = null;
					thisSession = new Session(
							UUID.randomUUID().toString(), 
							courseProgress.getUserId(), 
							courseProgress.getCourseId(), 
							"", 
							new ArrayList<Interaction>() );
					List<InteractionResponseRoscoe> interactionResponse = courseProgress.getInteractionResponses();
					courseProgress.getInteractionsCompleted()
								.stream()
								.forEachOrdered( interaction
				-> {
					if ( interactionDateTime != null ) 
						currentDateTime = new DateTime( interactionDateTime );
					interactionDateTime = new DateTime(interaction.getCompletionDateTime());
					if ( currentDateTime == null )
						currentDateTime = new DateTime(interaction.getCompletionDateTime()); 
					Duration duration = new Duration( currentDateTime, interactionDateTime);
					if ( duration.isShorterThan( new Duration(SESSION_TIMEOUT*60*1000 ) ) ) {
						// Same session
						// TODO: Check if there is a response
						Interaction response = courseProgress.getResponseIfExists(interaction);
						// TODO: then only insert into array
						if ( response != null )
							thisSession.insertInteraction( response );
						else
							thisSession.insertInteraction( interaction );
					}
					// start new session
					if ( thisSession.getUserInteractions().size() > 0 )
						sessions.add(thisSession);
					else {
						thisSession = new Session(
								UUID.randomUUID().toString(), 
								courseProgress.getUserId(), 
								courseProgress.getCourseId(), 
								"", 
								new ArrayList<Interaction>() );
					}
				});		
			});
		sessionRepository.save(sessions);
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json")
	public List<CourseProgress> test() {
			ArrayList<String> users = new ArrayList<String>();
			for (CourseProgress interaction : courseProgressRepository.findAll() ) {
				/*if ( users.contains( interaction.getUserId() ) )
					continue;
				else
					users.add( interaction.getUserId() );
					System.out.println( interaction );
					continue;*/
			}
			return courseProgressRepository.findByUserId("teach01");
    }
}
