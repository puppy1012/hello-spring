package hello.hello_spring.repository;

// Member 도메인 클래스 import
import hello.hello_spring.domain.Member;

import java.util.*;

// MemoryMemberRepository는 MemberRepository 인터페이스를 구현한 클래스
public class MemoryMemberRepository implements MemberRepository {

    // 회원 데이터를 저장할 Map 선언 (Key: 회원 ID, Value: Member 객체)
    private static Map<Long, Member> store = new HashMap<>();
    // 회원 ID를 자동 증가시킬 시퀀스 변수 (전역 static으로 유지)
    private static long sequence = 0L;

    // 회원을 저장하는 메서드
    @Override
    public Member save(Member member) {
        member.setId(++sequence); // 회원 ID를 하나 증가시켜 설정
        store.put(member.getId(), member); // Map에 ID를 키로 하여 회원 저장
        return member; // 저장된 회원 객체 반환
    }

    // ID로 회원을 찾는 메서드
    @Override
    public Optional<Member> findByID(Long id) {
        // Map에서 ID에 해당하는 회원을 꺼내 Optional로 감싸 반환 (null 안전)
        return Optional.ofNullable(store.get(id));
    }

    // 이름으로 회원을 찾는 메서드
    @Override
    public Optional<Member> findByName(String name) {
        // 저장된 값들 중 이름이 일치하는 회원을 찾아 Optional로 반환
        return store.values().stream() // 모든 회원들을 스트림으로 처리
                .filter(member -> member.getName().equals(name)) // 이름이 같은 경우 필터링
                .findAny(); // 조건에 맞는 첫 번째 결과 반환
    }

    // 저장된 모든 회원 목록 반환
    @Override
    public List<Member> findAll() {
        // Map의 values()를 List로 변환하여 반환
        return new ArrayList<>(store.values());
    }
    public void clearStore(){
        store.clear();
    }
}
