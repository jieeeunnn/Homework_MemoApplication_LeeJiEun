# Homework_MemoApplication
### Techkit 앱 스쿨 2기 | [2차] Android 평가 과제
### 💡 요구사항

1. 메모 관리 (MainActivity)
   - 상단 툴바의 `+` 를 누르면 메모 작성 화면이 나타난다.
   - RecyclerView의 항목은 메모의 제목과 작성 날짜를 보여준다.
   - RecyclerView의 항목을 누르면 슬라이드 3의 화면이 나타난다.


2. 메모 작성 (InputActivity)
   - `제목`과 `내용` 항목을 생성
   - 내용은 여러 줄 입력이 가능하도록 설정
   - 제목과 내용을 입력하고`V`를 누르면 메모 관리로 이동
   - 메모 관리 이동 시, 메모관리에서 아래 항목 보여주기
     - RecyclerView의 항목 보여주기
     - 날짜는 현재 날짜로 구해 사용
  

3. 메모 보기 (ShowActivity)
   - **슬라이드1]**의 RecyclerView에서 선택한 항목의 메모 내용이 보여지게 한다.
   - 메모의 내용은 TextField를 통해 보여주고 입력은 불가능하게 설정
   - 툴바에는 `수정`과 `삭제`가 있으며 수정, 삭제 아이콘은 자유롭게 설정
   - `수정`을 누르면 메모 수정 화면으로 이동
   - `삭제`를 누르면 현재 메모에 대해 삭제 처리
   - 삭제 후 메모 관리 화면으로 이동

  
4. 메모 수정 (ModifyActivity)
   - 수정하기 전의 내용을 TextField에 보여 줌
   - 수정 완료 후 `V` 를 누르면 메모 보기 화면으로 이동
   - 메모 보기 화면으로 이동 시 수정된 내용으로 보여 줌
   - 작성 날짜는 수정하지 않는다.
  
   
