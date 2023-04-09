public class Questions {

    /**
     *need to create a question and answer pair.
     *need to limit visibility -> created a separate questions class as an easy way to encapsulate the data.
     *using a nested static class (Question) so that it can be accessed without needing to access
          the outer Questions class. This way makes it easier to group related classes together, and
          limit the visibility of the Question class.
     */
    public static class Question { //nested static class
        private String question;
        private String answer;

        public Question(String question, String answer) { //this constructor initializes the private variables
            this.question = question;
            this.answer = answer;
        }
        //using two getter methods to get the values of the question and answer variables
        public String getQuestion() {
            return question;
        }
        public String getAnswer() {
            return answer;
        }
    }
}

