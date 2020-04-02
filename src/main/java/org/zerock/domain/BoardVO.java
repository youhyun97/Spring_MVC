package org.zerock.domain;

import java.util.Date;

import lombok.Data;

@Data // lombok을 이용해 getter(), setter(), toString()을 자동생성
public class BoardVO {

  private Long bno;
  private String title;
  private String content;
  private String writer;
  private Date regdate;
  private Date updateDate;
}
