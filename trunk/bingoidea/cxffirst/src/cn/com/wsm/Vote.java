package cn.com.wsm;

import javax.jws.WebService;

@WebService
public class Vote implements IVote
{
    private static int pointTotal;
    private static int userTotal;


    public int getVotePointTotal()
    {
        return pointTotal;
    }


    public int getVoteUserTotal()
    {
        return userTotal;
    }


    public boolean vote(String username, int point)
    {
        userTotal++;
        pointTotal += point;
        return true;
    }
}
