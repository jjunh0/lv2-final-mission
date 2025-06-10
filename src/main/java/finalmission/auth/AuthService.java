package finalmission.auth;

import finalmission.member.Member;
import finalmission.member.MemberRepository;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public String login(LoginRequest request) {
        Member member = memberRepository.findByEmailAndPassword(request.email(), request.password())
            .orElseThrow(NoSuchElementException::new);
        return jwtTokenProvider.getToken(member);
    }
}
