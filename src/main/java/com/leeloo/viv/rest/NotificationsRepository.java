package com.leeloo.viv.rest;

import java.util.*;

public class NotificationsRepository{

	private List<Notification> notifications = Arrays.asList(
    		new Notification("1", "Borobio", "Leeloo liked your work", "1", false),
            new Notification("2", "Borobio", "Erika commented your work", "3", false),
            new Notification("3", "Borobio", "Erika commented your work", "12", false),
            new Notification("4", "Borobio", "Leeloo liked your work", "7", true),
            new Notification("5", "Borobio", "Leeloo commented your work", "7", true),
            new Notification("6", "Erika", "Perico liked your work", "1", false),
            new Notification("7", "Leeloo", "Perico commented your work", "3", false),
            new Notification("8", "Leeloo", "Perico commented your work", "12", false),
            new Notification("9", "Leeloo", "Perico liked your work", "7", false),
            new Notification("10", "Erika", "Perico commented your work", "7", false)
        );


    public List<Notification> getUserNotifications(String user)
    {
    	List<Notification> userNotifications = new ArrayList<Notification>();
    	for(Notification notification : notifications)
		{
		    if (notification.User.toLowerCase().equals(user.toLowerCase())) {
		    	userNotifications.add(notification);
		    }
		}
        return userNotifications;
    }

    public List<Notification> getUserUnreadNotifications(String user){
        List<Notification> unread = new ArrayList<Notification>();

        List<Notification> userNotifications = getUserNotifications(user);

        for(Notification notification : userNotifications)
        {
            if (!notification.Read) {
                unread.add(notification);
            }
        }
        return unread;
    }
  

}