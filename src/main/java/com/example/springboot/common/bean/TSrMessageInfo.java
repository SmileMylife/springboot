package com.example.springboot.common.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * t_sr_message_info
 * @author 
 */
@Data
public class TSrMessageInfo implements Serializable {
    private Integer id;

    private String requestUrl;

    private String requestMethod;

    private String contentType;

    private String requestMessage;

    private String uploadBy;

    private Date crtTime;

    private String interName;

    private static final long serialVersionUID = 1L;
}