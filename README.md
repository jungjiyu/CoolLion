# 멋쟁이 사자처럼 3팀 관련 리파지토리
- board : 교내해커톤 게시판 프로젝트
- todo : 
    @OneToMany(mappedBy = "member")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();
}
