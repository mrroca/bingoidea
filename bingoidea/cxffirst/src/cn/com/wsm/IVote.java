package cn.com.wsm;

import javax.jws.WebService;
@WebService
public interface IVote
{
    public boolean vote(String username, int point);


    public int getVoteUserTotal();


    public int getVotePointTotal();
}
