package hello.member;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    public void initTable() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS member");
        jdbcTemplate.execute("CREATE TABLE member (member_id VARCHAR(255), name VARCHAR(255))");
    }

    public void save(Member member) {
        jdbcTemplate.update(
            "INSERT INTO member (member_id, name) VALUES (?, ?)",
            member.memberId(), member.name()
        );
    }

    public Member findById(String memberId) {
        return jdbcTemplate.queryForObject(
            "SELECT * FROM member WHERE member_id = ?",
            rowMapper(),
            memberId
        );
    }

    public List<Member> findAll() {
        return jdbcTemplate.query(
            "SELECT * FROM member",
            BeanPropertyRowMapper.newInstance(Member.class)
        );
    }

    private RowMapper<Member> rowMapper() {
        return (rs, rowNum) -> new Member(
            rs.getString("member_id"), rs.getString("name")
        );
    }
}
