package cn.com.bsoft.test;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class EventDao extends HibernateDaoSupport
{

    public void saveEvent(Event event)
    {
        /*SessionFactory sf = getHibernateTemplate().getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();*/
//        Transaction t = session.beginTransaction();
//        t.begin();
        getHibernateTemplate().save(event);
//        t.commit();
        //getHibernateTemplate().flush();

        /*session.getTransaction().commit();
        session.close();
        sf.close();*/
    }
}
