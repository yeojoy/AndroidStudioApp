package me.yeojoy.studio.dto;

/**
 * Created by yeojoy on 14. 12. 12..
 */
import com.google.gson.Gson;

import java.sql.Timestamp;
import java.util.Date;

public class Item {

    /** 정답 */
    public static final String CORRECT = "1";
    /** 오답 */
    public static final String INCORRECT = "0";

    /** 파라미터 : 헤더정보 */
    private String header;
    /** 파라미터 : 선택정보 */
    private String choices;
    /** 파라미터 :문항번호 */
    private String id;
    /** 파라미터 :유형 */
    private String type;
    /** 파라미터 :정렬종류 */
    private String orderKind;


    /** 학습자ID */
    private String studyUsrId;
    /** 문항등급 */
    private String grade;
    /** 정답여부 */
    private boolean correct;
    /** 결과메세지 */
    private String message;
    /** 이전 문항번호 */
    private String prevQuestionId;
    /** 다음 문항번호 */
    private String nextQuestionId;
    /** 사용자의 답 */
    private String[] answers;
    /** 문항한글내용 */
    private String questionKor;
    /** 지시문한글내용 */
    private String instructKor;
    /** 답안한글내용 */
    private String answerKorConts;
    /** 답안영문내용 */
    private String answerEnglConts;

    /** 문항번호코드 */
    private String questionNbrCde;
    /** 학습목표코드 */
    private String studyObjCde;
    /** 과목코드 */
    private String subjectCde;
    /** 문항구분코드 */
    private String questionDivCde;
    /** 학습등급코드 */
    private String levlCde;
    /** 문항유형코드 */
    private String questionTypCde;
    /** 공통문항여부 */
    private String commQuestionYn;
    /** 문항과금코드 */
    private String questionPriceCde;
    /** 차별요소코드 */
    private String diffElmtCde;
    /** 학습문항순번 */
    private String questionSeqNo;
    /** 업무구분코드 */
    private String busiDivCde;
    /** 학습결과구분코드 */
    private String studyRsltDivCde;
    /** 국카코드 */
    private String natltyCde;
    /** 문항내용 */
    private String questionConts;
    /** 보기내용 */
    private String examplConts;
    /** 시작일시 */
    private Timestamp strtDttm;
    /** 종료일시 */
    private Timestamp endDttm;


    /** 보기내용 */
    private String answerCde;
    /** 답변유형코드 */
    private String answerTypCde;
    /** 순서체크여부 */
    private String ordrChkYn;
    /** 답변내용 */
    private String answerConts;
    /** 풀이시간 */
    private long solvTime;

    /** 비고 */
    private String remrk;
    /** 입력자(User) */
    private String inptUsrId;
    /** 입력일시 */
    private Date inptDttm;
    /** 수정자 */
    private String updtUsrId;
    /** 수정일시 */
    private Date updtDttm;
    /** 작업자 IP */
    private String wrkIp;

    public String getHeader() {
        return header;
    }




    public void setHeader(String header) {
        this.header = header;
    }




    public String getChoices() {
        return choices;
    }




    public void setChoices(String choices) {
        this.choices = choices;
    }




    public String getId() {
        return id;
    }




    public void setId(String id) {
        this.id = id;
    }




    public String getType() {
        return type;
    }




    public void setType(String type) {
        this.type = type;
    }




    public String getOrderKind() {
        return orderKind;
    }




    public void setOrderKind(String orderKind) {
        this.orderKind = orderKind;
    }




    public String getStudyUsrId() {
        return studyUsrId;
    }




    public void setStudyUsrId(String studyUsrId) {
        this.studyUsrId = studyUsrId;
    }




    public String getGrade() {
        return grade;
    }




    public void setGrade(String grade) {
        this.grade = grade;
    }




    public boolean isCorrect() {
        return correct;
    }




    public void setCorrect(boolean correct) {
        this.correct = correct;
    }




    public String getMessage() {
        return message;
    }




    public void setMessage(String message) {
        this.message = message;
    }




    public String getPrevQuestionId() {
        return prevQuestionId;
    }




    public void setPrevQuestionId(String prevQuestionId) {
        this.prevQuestionId = prevQuestionId;
    }




    public String getNextQuestionId() {
        return nextQuestionId;
    }




    public void setNextQuestionId(String nextQuestionId) {
        this.nextQuestionId = nextQuestionId;
    }




    public String[] getAnswers() {
        return answers;
    }




    public void setAnswers(String[] answers) {
        this.answers = answers;
    }




    public String getQuestionKor() {
        return questionKor;
    }




    public void setQuestionKor(String questionKor) {
        this.questionKor = questionKor;
    }




    public String getInstructKor() {
        return instructKor;
    }




    public void setInstructKor(String instructKor) {
        this.instructKor = instructKor;
    }




    public String getAnswerKorConts() {
        return answerKorConts;
    }




    public void setAnswerKorConts(String answerKorConts) {
        this.answerKorConts = answerKorConts;
    }




    public String getAnswerEnglConts() {
        return answerEnglConts;
    }




    public void setAnswerEnglConts(String answerEnglConts) {
        this.answerEnglConts = answerEnglConts;
    }




    public String getQuestionNbrCde() {
        return questionNbrCde;
    }




    public void setQuestionNbrCde(String questionNbrCde) {
        this.questionNbrCde = questionNbrCde;
    }




    public String getStudyObjCde() {
        return studyObjCde;
    }




    public void setStudyObjCde(String studyObjCde) {
        this.studyObjCde = studyObjCde;
    }




    public String getSubjectCde() {
        return subjectCde;
    }




    public void setSubjectCde(String subjectCde) {
        this.subjectCde = subjectCde;
    }




    public String getQuestionDivCde() {
        return questionDivCde;
    }




    public void setQuestionDivCde(String questionDivCde) {
        this.questionDivCde = questionDivCde;
    }




    public String getLevlCde() {
        return levlCde;
    }




    public void setLevlCde(String levlCde) {
        this.levlCde = levlCde;
    }




    public String getQuestionTypCde() {
        return questionTypCde;
    }




    public void setQuestionTypCde(String questionTypCde) {
        this.questionTypCde = questionTypCde;
    }




    public String getCommQuestionYn() {
        return commQuestionYn;
    }




    public void setCommQuestionYn(String commQuestionYn) {
        this.commQuestionYn = commQuestionYn;
    }




    public String getQuestionPriceCde() {
        return questionPriceCde;
    }




    public void setQuestionPriceCde(String questionPriceCde) {
        this.questionPriceCde = questionPriceCde;
    }




    public String getDiffElmtCde() {
        return diffElmtCde;
    }




    public void setDiffElmtCde(String diffElmtCde) {
        this.diffElmtCde = diffElmtCde;
    }




    public String getQuestionSeqNo() {
        return questionSeqNo;
    }




    public void setQuestionSeqNo(String questionSeqNo) {
        this.questionSeqNo = questionSeqNo;
    }




    public String getBusiDivCde() {
        return busiDivCde;
    }




    public void setBusiDivCde(String busiDivCde) {
        this.busiDivCde = busiDivCde;
    }




    public String getStudyRsltDivCde() {
        return studyRsltDivCde;
    }




    public void setStudyRsltDivCde(String studyRsltDivCde) {
        this.studyRsltDivCde = studyRsltDivCde;
    }




    public String getNatltyCde() {
        return natltyCde;
    }




    public void setNatltyCde(String natltyCde) {
        this.natltyCde = natltyCde;
    }




    public String getQuestionConts() {
        return questionConts;
    }




    public void setQuestionConts(String questionConts) {
        this.questionConts = questionConts;
    }




    public String getExamplConts() {
        return examplConts;
    }




    public void setExamplConts(String examplConts) {
        this.examplConts = examplConts;
    }




    public Timestamp getStrtDttm() {
        return strtDttm;
    }




    public void setStrtDttm(Timestamp strtDttm) {
        this.strtDttm = strtDttm;
    }




    public Timestamp getEndDttm() {
        return endDttm;
    }




    public void setEndDttm(Timestamp endDttm) {
        this.endDttm = endDttm;
    }




    public String getAnswerCde() {
        return answerCde;
    }




    public void setAnswerCde(String answerCde) {
        this.answerCde = answerCde;
    }




    public String getAnswerTypCde() {
        return answerTypCde;
    }




    public void setAnswerTypCde(String answerTypCde) {
        this.answerTypCde = answerTypCde;
    }




    public String getOrdrChkYn() {
        return ordrChkYn;
    }




    public void setOrdrChkYn(String ordrChkYn) {
        this.ordrChkYn = ordrChkYn;
    }




    public String getAnswerConts() {
        return answerConts;
    }




    public void setAnswerConts(String answerConts) {
        this.answerConts = answerConts;
    }




    public long getSolvTime() {
        return solvTime;
    }




    public void setSolvTime(long solvTime) {
        this.solvTime = solvTime;
    }




    public String getRemrk() {
        return remrk;
    }




    public void setRemrk(String remrk) {
        this.remrk = remrk;
    }




    public String getInptUsrId() {
        return inptUsrId;
    }




    public void setInptUsrId(String inptUsrId) {
        this.inptUsrId = inptUsrId;
    }




    public Date getInptDttm() {
        return inptDttm;
    }




    public void setInptDttm(Date inptDttm) {
        this.inptDttm = inptDttm;
    }




    public String getUpdtUsrId() {
        return updtUsrId;
    }




    public void setUpdtUsrId(String updtUsrId) {
        this.updtUsrId = updtUsrId;
    }




    public Date getUpdtDttm() {
        return updtDttm;
    }




    public void setUpdtDttm(Date updtDttm) {
        this.updtDttm = updtDttm;
    }




    public String getWrkIp() {
        return wrkIp;
    }




    public void setWrkIp(String wrkIp) {
        this.wrkIp = wrkIp;
    }





    @Override
    public String toString() {

        try {
            return new Gson().toJson(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Item.class.getSimpleName() + " obj is null or empty.";
    }
}
