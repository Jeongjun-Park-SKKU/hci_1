# hci_1
안녕하세요.
HCI 과목 기말 작품의 주제에 맞는 코드 및 안드로이드 어플을 제작했습니다.

구현된 기능은 아래와 같습니다.
1. 사용자의 전면 카메라를 동작시킴
2. 카메라에서 눈을 검출하고 눈을 감고 뜨고를 확인함
3. 확인된 값에 따라서 눈을 3초 이상 감고 있을경우 전체 밝기 100, 소리를 10이라고 했을때 -10, -1씩 줄임
4. 만약 눈을 뜬다면 다시 초기 설정 값인 80, 5로 초기화함

구현 방식
1. 안드로이드 9 이후로는 백그라운드에서 전면 카메라 동작을 허가하지 않음
2. 이를 해결하기 위해서 구현은 S7 테블릿에서 했으며 화면 절반에는 youtube를 절반은 본 어플을 실행함

필요한 추가 기능
1. 시작, 종료 버튼: 지금 구현된 상황은 어플 시작과 동시에 시작함
2. 기타 레이아웃

   