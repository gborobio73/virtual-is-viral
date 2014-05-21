package com.leeloo.viv.rest;

import java.util.*;

public class WorkRepository{

	private List<Work> works = Arrays.asList(
    		new Work("1", "Erika","Building facade", "A picture of a building facade", "1"),
            new Work("2", "Erika","Anoter work", "Another work", "2"),
            new Work("3", "Leeloo","Painting", "A painting work", "3"),
            new Work("4", "Leeloo","Writting", "A postal card work", "4"),
            new Work("5", "Borobio", "A picture work", "A picture of Leeloo", "5"),
            new Work("6", "Erika","Building facade", "A picture of a building facade", "6"),
            new Work("7", "Erika","Anoter work", "Another work", "7"),
            new Work("8", "Leeloo","Painting", "A painting work", "8"),
            new Work("9", "Leeloo","Writting", "A postal card work", "9"),
            new Work("10", "Borobio", "A picture work", "A picture of Borobio", "10"),
            new Work("11", "Erika", "A picture work", "A picture of Erika", "11"),
            new Work("12", "Borobio", "A picture work", "A picture of Borobio", "12"),
            new Work("13", "Leeloo", "A picture work", "A picture of Leeloo", "13"),
            new Work("14", "Borobio", "A picture work", "A picture of Borobio", "14")
    	);


    public List<Work>  getAllWorks()
    {
        return works;
    }

    public List<Work> getUserWorks(String user)
    {
    	List<Work> userWorks = new ArrayList<Work>();
    	for(Work work : works)
		{
		    if (work.User.toLowerCase().equals(user.toLowerCase())) {
		    	userWorks.add(work);
		    }
		}
        return userWorks;
    }

    public Work getWork(String id)
    {
        for(Work work : works)
        {
            if (work.Id.toLowerCase().equals(id.toLowerCase())) {
                return work;
            }
        }
        return new NullWork();
    }
}