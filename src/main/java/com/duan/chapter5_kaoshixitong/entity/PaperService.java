package com.duan.chapter5_kaoshixitong.entity;

public interface PaperService {

    public ExamPaper genPaper();

    public int[] givescore( String answer[], String useranswer[], int score);

}