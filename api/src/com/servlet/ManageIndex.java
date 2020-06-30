package com.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.bean.Comment_anime;
import com.bean.Comment_news;
import com.bean.User;
import com.mybatis.MyBatiser;

/**
 * Servlet implementation class ManageIndex
 */
@WebServlet("/ManageIndex")
public class ManageIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageIndex() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Access-Control-Allow-Origin", "*");
		@SuppressWarnings("deprecation")
		MyBatiser myBatiser = new MyBatiser();
		List<User> users = myBatiser.selectAllUsers();
		List<Comment_news> comments=myBatiser.selectAllComment_news();
	    List<Comment_anime> comment_animes=myBatiser.selectAllComment_anime();
		System.out.println("get");
		Calendar c1 = Calendar.getInstance();
		int month;
		int day;
		Date tempdate;
		c1.add(Calendar.MONTH, 1);
		
		int man_value=0;
		int woman_value=0;
		int alien_value=0;
		for(int j = 0; j <users.size();j++) 
		{
	      if(users.get(j).getGender()==0)
	    		  alien_value++;
	      else  if(users.get(j).getGender()==1)
    		  man_value++;
	      else  if(users.get(j).getGender()==2)
    		  woman_value++;
	    }
		
		month=c1.get(Calendar.MONTH);
		day=c1.get(Calendar.DAY_OF_MONTH);
		String seven_day=month+"."+day;
		int seven_value=0;
        for(int j = 0; j <users.size();j++) 
		{
	      if(compare(c1,users.get(j).getRegister_time()))
	    		  seven_value++;
	    }	
		int seven_covalue=0;
		for(int j=0;j<comments.size();j++)
		{
			tempdate=new Date(comments.get(j).getCreate_time());
			if(compareComment(c1,tempdate))
			{
				seven_covalue++;
			}
		}
		for(int j=0;j<comment_animes.size();j++)
		{
			tempdate=new Date(comment_animes.get(j).getCreate_time());
			if(compareComment(c1,tempdate))
			{
				seven_covalue++;
			}
		}
		c1.add(Calendar.DATE, -1);
		
		month=c1.get(Calendar.MONTH);
		day=c1.get(Calendar.DAY_OF_MONTH);
		String six_day=month+"."+day;
		int six_value=0;
		for(int j = 0; j <users.size();j++) 
		{
	      if(compare(c1,users.get(j).getRegister_time()))
	    		  six_value++;
	    }
		int six_covalue=0;
		for(int j=0;j<comments.size();j++)
		{
			tempdate=new Date(comments.get(j).getCreate_time());
			if(compareComment(c1,tempdate))
			{
				six_covalue++;
			}
		}
		for(int j=0;j<comment_animes.size();j++)
		{
			tempdate=new Date(comment_animes.get(j).getCreate_time());
			if(compareComment(c1,tempdate))
			{
				six_covalue++;
			}
		}
		c1.add(Calendar.DATE, -1);
		
		month=c1.get(Calendar.MONTH);
		day=c1.get(Calendar.DAY_OF_MONTH);
		String five_day=month+"."+day;
		int five_value=0;
		for(int j = 0; j <users.size();j++) 
		{
	      if(compare(c1,users.get(j).getRegister_time()))
	    		  five_value++;
	    }
		int five_covalue=0;
		for(int j=0;j<comments.size();j++)
		{
			tempdate=new Date(comments.get(j).getCreate_time());
			if(compareComment(c1,tempdate))
			{
				five_covalue++;
			}
		}
		for(int j=0;j<comment_animes.size();j++)
		{
			tempdate=new Date(comment_animes.get(j).getCreate_time());
			if(compareComment(c1,tempdate))
			{
				five_covalue++;
			}
		}
		c1.add(Calendar.DATE, -1);
		
		month=c1.get(Calendar.MONTH);
		day=c1.get(Calendar.DAY_OF_MONTH);
		String four_day=month+"."+day;
		int four_value=0;
		for(int j = 0; j <users.size();j++) 
		{
	      if(compare(c1,users.get(j).getRegister_time()))
	    		  four_value++;
	    }
		int four_covalue=0;
		for(int j=0;j<comments.size();j++)
		{
			tempdate=new Date(comments.get(j).getCreate_time());
			if(compareComment(c1,tempdate))
			{
				four_covalue++;
			}
		}
		for(int j=0;j<comment_animes.size();j++)
		{
			tempdate=new Date(comment_animes.get(j).getCreate_time());
			if(compareComment(c1,tempdate))
			{
				four_covalue++;
			}
		}
		c1.add(Calendar.DATE, -1);
		
		month=c1.get(Calendar.MONTH);
		day=c1.get(Calendar.DAY_OF_MONTH);
		String three_day=month+"."+day;
		int three_value=0;
		for(int j = 0; j <users.size();j++) 
		{
	      if(compare(c1,users.get(j).getRegister_time()))
	    		  three_value++;
	    }
		int three_covalue=0;
		for(int j=0;j<comments.size();j++)
		{
			tempdate=new Date(comments.get(j).getCreate_time());
			if(compareComment(c1,tempdate))
			{
				three_covalue++;
			}
		}
		for(int j=0;j<comment_animes.size();j++)
		{
			tempdate=new Date(comment_animes.get(j).getCreate_time());
			if(compareComment(c1,tempdate))
			{
				three_covalue++;
			}
		}
		c1.add(Calendar.DATE, -1);
		
		
		month=c1.get(Calendar.MONTH);
		day=c1.get(Calendar.DAY_OF_MONTH);
		String two_day=month+"."+day;
		int two_value=0;
		for(int j = 0; j <users.size();j++) 
		{
	      if(compare(c1,users.get(j).getRegister_time()))
	    		  two_value++;
	    }
		int two_covalue=0;
		for(int j=0;j<comments.size();j++)
		{
			tempdate=new Date(comments.get(j).getCreate_time());
			if(compareComment(c1,tempdate))
			{
				two_covalue++;
			}
		}
		for(int j=0;j<comment_animes.size();j++)
		{
			tempdate=new Date(comment_animes.get(j).getCreate_time());
			if(compareComment(c1,tempdate))
			{
				two_covalue++;
			}
		}
		c1.add(Calendar.DATE, -1);
		
		month=c1.get(Calendar.MONTH);
		day=c1.get(Calendar.DAY_OF_MONTH);
		String one_day=month+"."+day;
		int one_value=0;
		for(int j = 0; j <users.size();j++) 
		{
	      if(compare(c1,users.get(j).getRegister_time()))
	    		   one_value++;
	    }
		int one_covalue=0;
		for(int j=0;j<comments.size();j++)
		{
			tempdate=new Date(comments.get(j).getCreate_time());
			if(compareComment(c1,tempdate))
			{
				one_covalue++;
			}
		}
		for(int j=0;j<comment_animes.size();j++)
		{
			tempdate=new Date(comment_animes.get(j).getCreate_time());
			if(compareComment(c1,tempdate))
			{
				one_covalue++;
			}
		}
		c1.add(Calendar.DATE, -1);
		
		month=c1.get(Calendar.MONTH);
		day=c1.get(Calendar.DAY_OF_MONTH);
		String zero_day=month+"."+day;
		int zero_covalue=0;
		for(int j=0;j<comments.size();j++)
		{
			tempdate=new Date(comments.get(j).getCreate_time());
			if(compareComment(c1,tempdate))
			{
				zero_covalue++;
			}
		}
		for(int j=0;j<comment_animes.size();j++)
		{
			tempdate=new Date(comment_animes.get(j).getCreate_time());
			if(compareComment(c1,tempdate))
			{
				zero_covalue++;
			}
		}
		c1.add(Calendar.DATE, -1);
		
		month=c1.get(Calendar.MONTH);
		day=c1.get(Calendar.DAY_OF_MONTH);
		String fone_day=month+"."+day;
		int fone_covalue=0;
		for(int j=0;j<comments.size();j++)
		{
			tempdate=new Date(comments.get(j).getCreate_time());
			if(compareComment(c1,tempdate))
			{
				fone_covalue++;
			}
		}
		for(int j=0;j<comment_animes.size();j++)
		{
			tempdate=new Date(comment_animes.get(j).getCreate_time());
			if(compareComment(c1,tempdate))
			{
				fone_covalue++;
			}
		}
		c1.add(Calendar.DATE, -1);
		
		month=c1.get(Calendar.MONTH);
		day=c1.get(Calendar.DAY_OF_MONTH);
		String ftwo_day=month+"."+day;
		int ftwo_covalue=0;
		for(int j=0;j<comments.size();j++)
		{
			tempdate=new Date(comments.get(j).getCreate_time());
			if(compareComment(c1,tempdate))
			{
				ftwo_covalue++;
			}
		}
		for(int j=0;j<comment_animes.size();j++)
		{
			tempdate=new Date(comment_animes.get(j).getCreate_time());
			if(compareComment(c1,tempdate))
			{
				ftwo_covalue++;
			}
		}
		c1.add(Calendar.DATE, -1);
		
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("one_value",one_value);
		jsonObject.put("one_day",one_day);
		jsonObject.put("one_covalue",one_covalue);
		jsonObject.put("two_value",two_value);
		jsonObject.put("two_day",two_day);
		jsonObject.put("two_covalue",two_covalue);
		jsonObject.put("three_value",three_value);
		jsonObject.put("three_day",three_day);
		jsonObject.put("three_covalue",three_covalue);
		jsonObject.put("four_value",four_value);
		jsonObject.put("four_day",four_day);
		jsonObject.put("four_covalue",four_covalue);
		jsonObject.put("five_value",five_value);
		jsonObject.put("five_day",five_day);
		jsonObject.put("five_covalue",five_covalue);
		jsonObject.put("six_value",six_value);
		jsonObject.put("six_day",six_day);
		jsonObject.put("six_covalue",six_covalue);
		jsonObject.put("seven_value",seven_value);
		jsonObject.put("seven_day",seven_day);
		jsonObject.put("seven_covalue",seven_covalue);
		jsonObject.put("alien_value", alien_value);
		jsonObject.put("man_value", man_value);
		jsonObject.put("woman_value", woman_value);
		jsonObject.put("zero_day",zero_day);
		jsonObject.put("zero_covalue",zero_covalue);
		jsonObject.put("fone_day",fone_day);
		jsonObject.put("fone_covalue",fone_covalue);
		jsonObject.put("ftwo_day",ftwo_day);
		jsonObject.put("ftwo_covalue",ftwo_covalue);
		
		String json = jsonObject.toJSONString();
	    //瀛樺叆response锛岃繑鍥炵粰瀹㈡埛绔�
		response.getWriter().write(json);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	private boolean compare(Calendar calendar,Date date)
	{
		int cayear,camonth,caday;
		int dayear,damonth,daday;
		cayear=calendar.get(Calendar.YEAR);
		camonth=calendar.get(Calendar.MONTH);
		caday=calendar.get(Calendar.DAY_OF_MONTH);
		dayear=date.getYear()+1900;
		damonth=date.getMonth()+1;
		if(damonth==13)
		{
			damonth=1;
			dayear+=1;
		}
		daday=date.getDate();
		//System.out.println("calendar:"+cayear+camonth+caday+"Date:"+" year"+dayear+" month"+damonth+" day"+daday);
		if(dayear<cayear)
			return true;
		else if(dayear>cayear)
			return false;
		else if(damonth<camonth)
			return true;
		else if(damonth>camonth)
			return false;
		else if(daday<caday||daday==caday)
			return true;
		else 
			return false;
	}
	
	private boolean compareComment(Calendar calendar,Date date)
	{
		int cayear,camonth,caday;
		int dayear,damonth,daday;
		cayear=calendar.get(Calendar.YEAR);
		camonth=calendar.get(Calendar.MONTH);
		caday=calendar.get(Calendar.DAY_OF_MONTH);	
		daday=date.getDate()+11;
		dayear=date.getYear()+1950;
		damonth=date.getMonth()+6;
		if(daday>30)
		{
			damonth+=1;
			daday=daday%30;
		}
		if(damonth>=13)
		{
			damonth=damonth%12;
			dayear+=1;
		}
		
		if(dayear<cayear)
			return true;
		else if(dayear>cayear)
			return false;
		else if(damonth<camonth)
			return true;
		else if(damonth>camonth)
			return false;
		else if(daday<caday||daday==caday)
			return true;
		else 
			return false;
	}
	
}
