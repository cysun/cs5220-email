package cs520.email;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Email")
public class Email extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Email()
    {
        super();
    }

    protected void doPost( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        String from = request.getParameter( "from" );
        String to = request.getParameter( "to" );
        String subject = request.getParameter( "subject" );
        String content = request.getParameter( "content" );

        Properties props = System.getProperties();
        props.put( "mail.smtp.host", "localhost" );
        Session session = Session.getInstance( props );

        Message msg = new MimeMessage( session );
        try
        {
            msg.setFrom( new InternetAddress( from ) );
            msg.setRecipient( RecipientType.TO, new InternetAddress( to ) );
            msg.setSubject( subject );
            msg.setText( content );

            Transport.send( msg );
        }
        catch( Exception e )
        {
            throw new ServletException( e );
        }

        response.sendRedirect( "Email.html" );
    }

}
