import { Typography } from "@mui/material";
import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import { getEventById, getEventSummary } from "../api/EventApi";
import EventFeedbackForm from "../forms/EventFeedbackForm";
import { MainContainer } from "../styles/StyledComponents";

function EventInfo() {
  const [eventInfo, setEventInfo] = useState({});
  const [eventSummary, setEventSummary] = useState({});
  const location = useLocation(); //getting the id passed down from clicking the EventCard

  const sentimentOrder = ["Positive", "Neutral", "Negative"];

  const fetchEvent = async () => {
    try {
      const response = await getEventById(location.state);
      setEventInfo(response.data);
    } catch (error) {
      console.error("Error fetching event:", error);
    }
  };

  const fetchEventSummary = async () => {
    try {
      const response = await getEventSummary(location.state);
      setEventSummary(response.data);
    } catch (error) {
      console.error("Error fetching event summary:", error);
    }
  };

  useEffect(() => {
    fetchEvent();
    fetchEventSummary();
  }, []);

  return (
    <>
      <Typography>{eventInfo.title}</Typography>
      <Typography>{eventInfo.description}</Typography>
      {sentimentOrder.map((key) => (
        <Typography key={key}>
          {key}: {eventSummary[key]}
        </Typography>
      ))}
      <EventFeedbackForm eventId={eventInfo.id} />
      {eventInfo.eventFeedbackList?.map((eventFeedback, index) => (
        <div key={index}>
          <Typography>{eventFeedback.timeStamp}</Typography>
          <Typography>{eventFeedback.feedback}</Typography>
          <Typography>{eventFeedback.eventSentimentDTO.label}</Typography>
          <Typography>{eventFeedback.eventSentimentDTO.score}</Typography>
        </div>
      ))}
    </>
  );
}
export default EventInfo;
