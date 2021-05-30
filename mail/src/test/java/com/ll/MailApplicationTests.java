package com.ll;

import com.ll.model.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;

@SpringBootTest
class MailApplicationTests {

	@Autowired
	JavaMailSender javaMailSender;

	@Test
	void contextLoads() {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(发送人的QQ邮箱号);
		simpleMailMessage.setTo("1744163625@qq.com");
		simpleMailMessage.setSentDate(new Date());
		simpleMailMessage.setSubject("皮皮儿砸收到请回答");
		simpleMailMessage.setText("邮件测试！！！");
		javaMailSender.send(simpleMailMessage);
	}

	@Test
	void test1() throws MessagingException {
		File file = new File("C:/Users/22370/Desktop/工作簿.xlsx");


		MimeMessage message = javaMailSender.createMimeMessage();
		// multipart=true是复合邮件
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
		messageHelper.setFrom(发送人的QQ邮箱号);
		messageHelper.setTo(接收人的QQ邮箱号);
		messageHelper.setSentDate(new Date());
		messageHelper.setSubject("邮件主题test2");
		messageHelper.setText("邮件测试test2！！！");
		messageHelper.addAttachment(file.getName(),file);
		javaMailSender.send(message);
	}

	@Test
	void test2() throws MessagingException, IOException, TemplateException {
		MimeMessage message = javaMailSender.createMimeMessage();
		// multipart=true是复合邮件
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
		messageHelper.setFrom(发送人的QQ邮箱号);
		messageHelper.setTo(接收人的QQ邮箱号);
		messageHelper.setSentDate(new Date());
		messageHelper.setSubject("邮件主题test2");
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
		configuration.setClassLoaderForTemplateLoading(MailApplication.class.getClassLoader(),"mail");
		Template template = configuration.getTemplate("mail.ftl");
		User user=new User();
		user.setPosition("java工程师");
		user.setSal(999);
		user.setUsername("张三");
		user.setCompany("阿里巴巴");
		StringWriter out = new StringWriter();
		template.process(user,out);
		messageHelper.setText(out.toString(),true);
		javaMailSender.send(message);
	}

	@Autowired
	TemplateEngine templateEngine;
	void test3() throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		// multipart=true是复合邮件
		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
		messageHelper.setFrom(发送人的QQ邮箱号);
		messageHelper.setTo(接收人的QQ邮箱号);
		messageHelper.setSentDate(new Date());
		messageHelper.setSubject("邮件主题test2");
		User user=new User();
		user.setPosition("java工程师");
		user.setSal(999);
		user.setUsername("张三");
		user.setCompany("阿里巴巴");
		Context ctx=new Context();
		ctx.setVariable("username","张三");
		ctx.setVariable("position","java工程师");
		ctx.setVariable("sal","999");
		ctx.setVariable("company","阿里巴巴");
		String text = templateEngine.process("mail.html", ctx);// ctx上下文
		messageHelper.setText(text,true);
		javaMailSender.send(message);
	}
}
