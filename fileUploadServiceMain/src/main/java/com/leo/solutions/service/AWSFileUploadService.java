package com.leo.solutions.service;
/*
 * @author love.bisaria on 24/12/18
 */

import com.jcraft.jsch.*;
import com.leo.solutions.config.AWSFTPServerConfig;
import com.leo.solutions.domain.FileQueuedResponse;
import com.leo.solutions.util.AppConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

@Service
public class AWSFileUploadService implements FileUploadService {

    private static final Logger log = LoggerFactory.getLogger(AWSFileUploadService.class);

    @Inject
    private AWSFTPServerConfig ftpServerConfig;

    @Override
    public FileQueuedResponse createFileUploadJob(String localFileName, String localFilePath){

        //generate uuid for the fileUpload job
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();

        return new FileQueuedResponse.Builder(randomUUIDString)
                .withFileName(localFileName)
                .withTimestamp(AppConstants.format.format(new Date()))
                .build();
    }

    @Override
    public boolean uploadFile(String localFileName, String localFilePath)  {

        Session session = createSession(ftpServerConfig);
        ChannelSftp channelSftp = null;
        Channel channel = null;
        try{
            session.connect();
            channel = session.openChannel("sftp");
            channel.connect();

            System.out.println("shell channel connected....");

            channelSftp = (ChannelSftp) channel;

            System.out.println("Starting File Upload....");
            channelSftp.put(localFilePath + "/" + localFileName, ftpServerConfig.getRemotePath());
            System.out.println("File Uploaded success....");


        } catch(JSchException | SftpException e){
            log.error("Exception: ", e);
        } finally {
            if(channelSftp !=null){
                channelSftp.disconnect();
                channelSftp.exit();
            }

            if(channel != null) channel.disconnect();
            if(session != null) session.disconnect();
        }

        return true;

    }

    private static  Session createSession(AWSFTPServerConfig ftpServerConfig){

        try{

            JSch jsch = new JSch();


            //add identity
            if(!StringUtils.isEmpty(ftpServerConfig.getCertPath())){
                if(!StringUtils.isEmpty(ftpServerConfig.getCertPass())){
                    jsch.addIdentity(ftpServerConfig.getCertPath(), ftpServerConfig.getCertPass());
                }else{
                    jsch.addIdentity(ftpServerConfig.getCertPath());
                }

            }

            Properties config = new Properties();

            //TODO: find out what this means
            config.put("StrictHostKeyChecking", "no");

            Session session = jsch.getSession(ftpServerConfig.getUser()
                    ,ftpServerConfig.getHost()
                    ,ftpServerConfig.getPort());

            session.setConfig(config);

            return session;


        }catch(JSchException e){
            log.error("Exception: ", e);
            return null;
        }

    }
}
