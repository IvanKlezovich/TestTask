package com.example.testtask.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponseDto<T> {

  private List<T> content;
  private int totalPages;
  private long totalElements;
  private int size;
  private int number;
}
