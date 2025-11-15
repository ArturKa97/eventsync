import HTTP from ".";

export function addEvent(workoutSession) {
  return HTTP.post("/events", workoutSession);
}

export function getEvents() {
  return HTTP.get("/events");
}
