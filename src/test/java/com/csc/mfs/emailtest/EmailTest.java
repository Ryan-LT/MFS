package com.csc.mfs.emailtest;


import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;


@RunWith(SpringJUnit4ClassRunner.class)
public class EmailTest {


	@Autowired
	private GreenMail smtpServer;

	@Before
	public void setUp() throws Exception {
		smtpServer = new GreenMail(new ServerSetup(25, null, "smtp"));
		smtpServer.start();
	}

	@After
	public void tearDown() throws Exception {
		smtpServer.stop();
	}


}

/**
 * 
 * @Autowired
	    private JavaMailSenderImpl mailSender;

	    private GreenMail testSmtp;

	    @Before
	    public void testSmtpInit(){
	        testSmtp = new GreenMail(ServerSetupTest.SMTP);
	        testSmtp.start();

	        //don't forget to set the test port!
	        mailSender.setPort(3025);
	        mailSender.setHost("localhost");
	    }

	    @Test
	    public void testEmail() throws InterruptedException, MessagingException {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setFrom("test@sender.com");
	        message.setTo("test@receiver.com");
	        message.setSubject("test subject");
	        message.setText("test message");
	        mailSender.send(message);

	        Message[] messages = testSmtp.getReceivedMessages();
	        assertEquals(1, messages.length);
	        assertEquals("test subject", messages[0].getSubject());
	        String body = GreenMailUtil.getBody(messages[0]).replaceAll("=\r?\n", "");
	        assertEquals("test message", body);
	    }

	    @After
	    public void cleanup(){
	        testSmtp.stop();
	    }
 */
