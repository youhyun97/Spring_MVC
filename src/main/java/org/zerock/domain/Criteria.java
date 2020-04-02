package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Setter // lombok을 이용해서 getter, setter 처리
@Getter
// 검색기준
public class Criteria {

  private int pageNum; // 현재 페이지 번호
  private int amount; // 데이터 수
  
  private String type;
  private String keyword;

  public Criteria() {
    this(1, 10); // 기본값을 1page, 10개로 지정해서 처리
  }

  public Criteria(int pageNum, int amount) {
    this.pageNum = pageNum;
    this.amount = amount;
  }
  
  public String[] getTypeArr() {
    
    return type == null? new String[] {}: type.split("");
  }
}
