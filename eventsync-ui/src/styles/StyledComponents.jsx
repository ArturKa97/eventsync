import styled from "@emotion/styled";
import {
  AppBar,
  Box,
  Typography,
  Container,
  Card,
  CardContent,
  CardActionArea,
} from "@mui/material";

export const AppBox = styled(Box)({
  display: "flex",
  flexDirection: "column",
  minHeight: "100vh",
});

export const StyledHeader = styled(AppBar)({
  position: "sticky",
  display: "flex",
  alignItems: "center",
});

export const HeaderLogoBox = styled(Box)({
  display: "flex",
  alignItems: "center",
  gap: "0.4rem",
});

export const LogoTypography = styled(Typography)({
  fontSize: "2.0rem",
  letterSpacing: "0.15rem",
  fontWeight: "600",
});

export const AppRoutesBox = styled(Box)({
  flex: 1,
});

export const MainContainer = styled(Container)({
  display: "flex",
  flexDirection: "row",
  flexWrap: "wrap",
  maxWidth: "1200",
  padding: "4.8rem 0 9.6rem 0",
  margin: "0 auto",
});
export const MainContainerColumn = styled(Container)({
  display: "flex",
  flexDirection: "column",
  flexWrap: "wrap",
  maxWidth: "1200",
  padding: "4.8rem 0 9.6rem 0",
  margin: "0 auto",
});

export const StyledCard = styled(Card)({
  width: 270,
  height: 200,
  margin: "0.5rem 0.5rem",
});

export const StyledCardActionArea = styled(CardActionArea)({
  height: "100%",
});

export const StyledCardContent = styled(CardContent)({
  display: "flex",
  flexDirection: "column",
  height: "100%",
});

export const StyledTitleTypography = styled(Typography)({
  flex: 1,
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
  whiteSpace: "normal",
  wordBreak: "break-word",
  margin: "0rem 0rem 0rem 0.5rem",
  borderBottom: "2px solid rgb(0, 0, 0)",
});
export const StyledDescriptionTypography = styled(Typography)({
  flex: 1,
  display: "flex",
  whiteSpace: "normal",
  wordBreak: "break-word",
  margin: "0.5rem 0rem 0rem 0.5rem",
});

export const FormBox = styled(Box)({
  width: "100%",
  display: "flex",
  flexDirection: "column",
  alignItems: "center",
  padding: "1rem 0",
  borderBottom: "2px solid rgb(0, 0, 0)",
});
export const FeedbackFormBox = styled(Box)({
  display: "flex",
  flexDirection: "column",
  alignItems: "left",
});

export const FormTextFieldBox = styled(Box)({
  display: "flex",
  gap: "2.4rem",
  alignItems: "center",
  margin: "0.5rem",
});

export const FormActionButtonBox = styled(Box)({
  display: "flex",
  alignItems: "center",
  justifyContent: "center",
});

export const EventInfoBox = styled(Box)({
  display: "flex",
  flexDirection: "column",
  padding: "1.0rem",
  flex: 3,
  margin: "1.0rem 0rem",
});
export const EventSummaryBox = styled(Box)({
  display: "flex",
  flexDirection: "column",
  padding: "1.0rem",
  flex: 1,
  justifyContent: "center",
  alignItems: "left",
  margin: "1.0rem 0rem",
  border: "2px solid rgb(0, 0, 0)",
});

export const EventMainBox = styled(Box)({
  display: "flex",
  flexDirection: "row",
  padding: "1.0rem",
  margin: "1.0rem 0rem",
  borderBottom: "2px solid rgb(0, 0, 0)",
});

export const SingleFeedbackBox = styled(Box)({
  display: "flex",
  flexDirection: "column",
  gap: "0.5rem",
  padding: "1.0rem",
  margin: "1.0rem 0rem",
  border: "2px solid rgb(0, 0, 0)",
});
