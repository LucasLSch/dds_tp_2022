package security.user;

import domain.exceptions.NullMemberException;
import domain.organization.DocType;
import domain.organization.Member;
import domain.territories.TerritorialSectorAgent;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.IOException;

@Setter
@Getter
public class Registration {

    private Member member;

    private TerritorialSectorAgent territorialSectorAgent;

    private User user;

    private Registration registerUser(User someUser) {
        setUser(someUser);
        return this;
    }

    public Registration registerStandardUser(@NonNull String username, @NonNull String password) throws IOException {
        validateNullMember();
        return registerUser(new StandardUser(username, password, getMember()));
    }

    private void validateNullMember() {
        if(getMember() == null) {
            throw new NullMemberException("Member");
        }
    }

    public Registration registerAdminUser(String username, String password) throws IOException {
        return registerUser(new Administrator(username, password));
    }

    public Registration registerAgentUser(String username, String password) throws IOException {
        validateNullSectorAgent();
        return registerUser(new TerritorialAgentUser(username, password, getTerritorialSectorAgent()));
    }

    private void validateNullSectorAgent() {
        if(getTerritorialSectorAgent() == null) {
            throw new NullMemberException("TerritorialSectorAgent");
        }
    }

    public Registration setMember(
            @NonNull String name,
            @NonNull String surname,
            @NonNull DocType docType,
            @NonNull String idNumber
    ) {
        setMember(new Member(name, surname, docType, idNumber));
        return this;
    }

    public Registration setSectorAgent() {
        setTerritorialSectorAgent(new TerritorialSectorAgent());
        return this;
    }

    public User build() {
        return getUser();
    }
}
