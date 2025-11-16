import HTTP from ".";

export function addEvent(event) {
  return HTTP.post("/events", event);
}

export function getEvents() {
  return HTTP.get("/events");
}

export function getEventById(eventId) {
  return HTTP.get(`/events/${eventId}`);
}

export function addEventFeedback(eventId, eventFeedback) {
  return HTTP.post(`/events/${eventId}/feedback`, eventFeedback);
}

export function getEventSummary(eventId) {
  return HTTP.get(`/events/${eventId}/summary`);
}
