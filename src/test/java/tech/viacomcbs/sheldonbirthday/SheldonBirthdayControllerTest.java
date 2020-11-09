package tech.viacomcbs.sheldonbirthday;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SheldonBirthdayControllerTest {

    @Test
    @DisplayName("Passes result between birthday tasks")
    void passesResultBetweenBirthdayTasks() {
        // given
        SheldonBirthdayController sheldonBirthdayController = new SheldonBirthdayController(
            List.of(new FakeTask1(), new FakeTask2()));
        // when
        String birthday = sheldonBirthdayController.birthday();

        // then
        assertThat(birthday).isEqualTo("FakeResult2{fakeResult1=FakeResult1{startResult=''}}");
    }

    static class FakeTask1 implements BirthdayTask<String, FakeResult1> {

        @Override
        public FakeResult1 perform(String source) {
            return new FakeResult1(source);
        }
    }

    static class FakeTask2 implements BirthdayTask<FakeResult1, FakeResult2> {
        @Override
        public FakeResult2 perform(FakeResult1 source) {
            return new FakeResult2(source);
        }
    }

    static class FakeResult1 {
        String startResult;

        public FakeResult1(String startResult) {
            this.startResult = startResult;
        }

        @Override
        public String toString() {
            return "FakeResult1{" +
                   "startResult='" + startResult + '\'' +
                   '}';
        }
    }

    static class FakeResult2 {
        FakeResult1 fakeResult1;

        public FakeResult2(FakeResult1 fakeResult1) {
            this.fakeResult1 = fakeResult1;
        }

        @Override
        public String toString() {
            return "FakeResult2{" +
                   "fakeResult1=" + fakeResult1 +
                   '}';
        }
    }
}