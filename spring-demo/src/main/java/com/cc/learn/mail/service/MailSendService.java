package com.cc.learn.mail.service;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 发送邮件的Service
 *
 * @author cc
 **/
@Slf4j
@Service
public class MailSendService {

    @Resource
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送邮件
     *
     * @param emailMessageVO  邮件实体
     */
    public void sendMail(EmailMessageVO emailMessageVO) throws MessagingException, IOException {
        MimeMessage message = sender.createMimeMessage();

        //true表示需要创建一个multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        if (CollectionUtils.isNotEmpty(emailMessageVO.getTo())) {
            String[] strings = emailMessageVO.getTo().toArray(new String[emailMessageVO.getTo().size()]);
            helper.setTo(strings);
        }
        if (CollectionUtils.isNotEmpty(emailMessageVO.getCc())) {
            String[] strings = emailMessageVO.getCc().toArray(new String[emailMessageVO.getCc().size()]);
            helper.setCc(strings);
        }
        if (CollectionUtils.isNotEmpty(emailMessageVO.getBcc())) {
            String[] strings = emailMessageVO.getBcc().toArray(new String[emailMessageVO.getBcc().size()]);
            helper.setBcc(strings);
        }

        if (CollectionUtils.isNotEmpty(emailMessageVO.getReplyTo())) {
            // 回复暂时只支持一个人
            String s = emailMessageVO.getReplyTo().get(0);
            helper.setReplyTo(s);
        }
        if (StringUtils.isNotEmpty(emailMessageVO.getSubject())) {
            helper.setSubject(emailMessageVO.getSubject());
        }
        if (StringUtils.isNotEmpty(emailMessageVO.getContent())) {
            //判断邮件的类型
            EmailContentType contentType = emailMessageVO.getContentType();
            if (EmailContentType.HTML.equals(contentType)) {
                helper.setText(emailMessageVO.getContent(),true);
            }else {
                helper.setText(emailMessageVO.getContent());
            }
        }
        if (CollectionUtils.isNotEmpty(emailMessageVO.getAttachments())) {
            for (String s : emailMessageVO.getAttachments()) {
                File file;
                URL url = new URL(s);
                String fileName = s.substring(s.lastIndexOf("/")+1);
                if ("file".equalsIgnoreCase(url.getProtocol())) {
                    // 文件类型
                    file = FileUtils.toFile(new URL(s));
                } else {
                    //http的形式
                 file = new File(System.getProperty("java.io.tmpdir")+File.separator+fileName);
                 FileUtils.copyURLToFile(url,file);
                }
                FileSystemResource fileSystemResource = new FileSystemResource(file);
                helper.addAttachment(fileName, fileSystemResource);
            }
        }
        sender.send(message);
    }
}
