package be.leerstad.evaluatieoefeningen.sessions.test;

import be.leerstad.evaluatieoefeningen.sessions.Examination;
import be.leerstad.evaluatieoefeningen.sessions.Inscription;
import be.leerstad.evaluatieoefeningen.sessions.Session;
import be.leerstad.evaluatieoefeningen.sessions.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class SessionTest {

    private Inscription inscription2;
    private Inscription inscription1;
    private Inscription inscription4;
    private Inscription inscription3;
    private Inscription inscription5;
    private Inscription inscription6;
    private Session session;
    private Student student1;
    private Student student2;
    private Student student3;
    private Student student4;
    private Student student5;
    private Student student6;
    private Examination examination1 = new Examination(1);
    private Examination examination2 = new Examination(2);
    private Examination examination3 = new Examination(3);
    private Examination examination4 = new Examination(4);
    private Examination examination4bis = new Examination(4);
    private Examination examination5 = new Examination(0.5d);

    @Before
    public void setUp() throws Exception {
        session = new Session("Burotica voor senioren");
        student1 = new Student("22456", "ALBERT");
        student2 = new Student("74874", "DEVILLIER");
        student3 = new Student("12345", "MONIN");
        student4 = new Student("9111111", "SCORNIET");
        student5 = new Student("9111112", "SCORNIET");
        student6 = new Student("904125", "VANBLADE");

    }


    private void addStudentsInSession() {
        inscription1 = new Inscription(student1, examination5);
        inscription2 = new Inscription(student2, examination4bis);
        inscription3 = new Inscription(student3, examination4);
        inscription4 = new Inscription(student4, examination3);
        inscription5 = new Inscription(student5, examination2);
        inscription6 = new Inscription(student6, examination1);
        session.addInscription(inscription2);

        session.addInscription(inscription1);

        session.addInscription(inscription4);

        session.addInscription(inscription3);

        session.addInscription(inscription5);

        session.addInscription(inscription6);

    }

    @Test
    public void testAddInscription() {
        session = new Session("Burotica voor senioren");
        Examination e = new Examination(15.6);

        assertEquals("the session is not correct", 0, session.getNbrStudents());

        assertTrue("inscription should have been done", session
                .addInscription(new Inscription(student1, e)));
        assertEquals("the session is not updated", 1, session.getNbrStudents());

        assertTrue("inscription should have been done", session
                .addInscription(new Inscription(student2, e)));
        assertEquals("the session is not updated", 2, session.getNbrStudents());

        assertTrue("inscription should have been done", session
                .addInscription(new Inscription(student3, e)));
        assertEquals("the session is not updated", 3, session.getNbrStudents());

        assertTrue("inscription should have been done", session
                .addInscription(new Inscription(student4, e)));
        assertEquals("the session is not updated", 4, session.getNbrStudents());

        assertTrue("inscription should have been done", session
                .addInscription(new Inscription(student5, e)));
        assertEquals("the session is not updated", 5, session.getNbrStudents());

        Assert.assertFalse("no twice same student inscribe in a same session", session
                .addInscription(new Inscription(student5, e)));
        assertEquals("the session size must not have been updated", 5, session
                .getNbrStudents());

        assertTrue("inscription should have been done", session
                .addInscription(new Inscription(student6, e)));
        assertEquals("the session is not updated", 6, session.getNbrStudents());
    }

    @Test
    public void testGetNbrStudents() {
        addStudentsInSession();
        assertEquals("Problem with getNbrStudent", 6, session.getNbrStudents());
    }

    @Test
    public void testGetStudentResult() {
        addStudentsInSession();
        assertEquals("Problem with getStudentResult", 0.5d, session
                .getStudentResult(student1.getMatriculation()), 0.01);

        assertEquals("Problem with getStudentResult if no value in", -1,
                session.getStudentResult("NOTIN"), 0.01);
    }

    @Test
    public void testGetInscriptionList() {
        addStudentsInSession();
        List list = session.getInscriptionList();
        assertEquals("the list size is not correct", 6, list.size());
        assertEquals("problem with sorting on name+matriculation", 0, list
                .indexOf(inscription1));

        assertEquals("problem with sorting on name+matriculation", 2, list
                .indexOf(inscription3));

        assertEquals("problem with sorting on name+matriculation", 1, list
                .indexOf(inscription2));

        assertEquals("problem with sorting on name+matriculation", 3, list
                .indexOf(inscription4));

        assertEquals("problem with sorting on name+matriculation", 4, list
                .indexOf(inscription5));

        assertEquals("problem with sorting on name+matriculation", 5, list
                .indexOf(inscription6));


    }

    @Test
    public void testGetInscriptionResultList() {
        addStudentsInSession();
        List<Inscription> list = session.getInscriptionResultList();
        assertEquals("the list size is not correct", 6, list.size());
        assertEquals("problem with sorting on result", 1, list
                .indexOf(inscription3));

        assertEquals("problem with sorting on result", 0, list
                .indexOf(inscription2));

        assertEquals("problem with sorting on result", 2, list
                .indexOf(inscription4));

        assertEquals("problem with sorting on result", 3, list
                .indexOf(inscription5));

        assertEquals("problem with sorting on result", 4, list
                .indexOf(inscription6));

        assertEquals("problem with sorting on result", 5, list
                .indexOf(inscription1));
    }


    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveInscription() {
        addStudentsInSession();
        List<Inscription> list = session.getInscriptionList();
        list.remove(0);


    }

}
