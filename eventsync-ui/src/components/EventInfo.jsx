import { Typography } from "@mui/material";
import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import { getEventById, getEventSummary } from "../api/EventApi";
import EventFeedbackForm from "../forms/EventFeedbackForm";
import {
  EventInfoBox,
  EventMainBox,
  EventSummaryBox,
  MainContainerColumn,
  StyledDescriptionTypography,
  StyledTitleTypography,
} from "../styles/StyledComponents";
import SentimentNeutralIcon from "@mui/icons-material/SentimentNeutral";
import SentimentVeryDissatisfiedIcon from "@mui/icons-material/SentimentVeryDissatisfied";
import SentimentSatisfiedAltIcon from "@mui/icons-material/SentimentSatisfiedAlt";

function EventInfo() {
  const [eventInfo, setEventInfo] = useState({});
  const [eventSummary, setEventSummary] = useState({});
  const location = useLocation(); //getting the id passed down from clicking the EventCard

  const sentimentOrder = ["Positive", "Neutral", "Negative"];
  const sentimentIcons = {
    Positive: <SentimentSatisfiedAltIcon sx={{ color: "green" }} />,
    Neutral: <SentimentNeutralIcon sx={{ color: "yellow" }} />,
    Negative: <SentimentVeryDissatisfiedIcon sx={{ color: "red" }} />,
  };

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
    <MainContainerColumn>
      <EventMainBox>
        <EventInfoBox>
          <StyledTitleTypography variant="h4">
            {eventInfo.title}
          </StyledTitleTypography>
          <StyledDescriptionTypography variant="h6">
            {eventInfo.description}
          </StyledDescriptionTypography>
        </EventInfoBox>
        <EventSummaryBox>
          <Typography variant="h5">Event Review Summary:</Typography>
          {sentimentOrder.map((key) => (
            <Typography variant="h6" key={key}>
              {key}: {eventSummary[key]} {sentimentIcons[key]}
            </Typography>
          ))}
        </EventSummaryBox>
      </EventMainBox>
      <EventFeedbackForm eventId={eventInfo.id} />
      {eventInfo.eventFeedbackList?.map((eventFeedback, index) => (
        <div key={index}>
          <Typography>{eventFeedback.timeStamp}</Typography>
          <Typography>{eventFeedback.feedback}</Typography>
          <Typography>{eventFeedback.eventSentimentDTO.label}</Typography>
          <Typography>{eventFeedback.eventSentimentDTO.score}</Typography>
        </div>
      ))}
    </MainContainerColumn>
  );
}
export default EventInfo;
