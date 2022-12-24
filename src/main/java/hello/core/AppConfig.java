package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Configuration, Bean을 사용하면 스프링 컨테이너에 등록된다.
@Configuration
public class AppConfig { // 어플리케이션 전체 설정, 구성
    // 공연 기획자

    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
//        new MemoryMemberRepository(); 를 memberRepository로 바꿔줌 아래에 전부 적용, 중복 new 를 바꿔줌
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    // 생성자 주입 dip 위반하지 않음
    // public OrderService orderService

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){         // 구성 역할만 바꾸면 해결된다.
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
