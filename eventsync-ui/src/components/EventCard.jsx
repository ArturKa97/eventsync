import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";
import CardActionArea from "@mui/material/CardActionArea";
import {
  StyledCard,
  StyledCardActionArea,
  StyledCardContent,
  StyledDescriptionTypography,
  StyledTitleTypography,
} from "../styles/StyledComponents";

function EventCard({ title, description, onClick }) {
  return (
    <StyledCard>
      <StyledCardActionArea onClick={onClick}>
        <StyledCardContent>
          <StyledTitleTypography variant="h5">{title}</StyledTitleTypography>
          <StyledDescriptionTypography variant="body2">
            {description}
          </StyledDescriptionTypography>
        </StyledCardContent>
      </StyledCardActionArea>
    </StyledCard>
  );
}
export default EventCard;
