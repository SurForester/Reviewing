public class Person {
    String firstName, String lastName,
    String middleName, String country, String address, String phone, int age, String gender;

    public static class Builder {
        private Engine engine;
        private Transmission transmission;
        private Body body;
        private Brakes brakes;
        private Pedals pedals;

        public Builder addEngine(Engine engine) {
            this.engine = engine;
            return this;
        }

        public Builder addTransmission(Transmission transmission) {
            this.transmission = transmission;
            return this;
        }

        public Builder addBody(Body body) {
            this.body = body;
            return this;
        }

        public Car build(){
            return new Car();
        }
    }
}
