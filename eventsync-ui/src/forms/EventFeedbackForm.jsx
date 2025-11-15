import { Form, Field, Formik } from "formik";
import {
  FormActionButtonBox,
  FormBox,
  FormTextFieldBox,
} from "../styles/StyledComponents";
import { Button, TextField } from "@mui/material";
import { addEventFeedback } from "../api/EventApi";

function EventFeedbackForm({ eventId }) {
  const onSubmit = async (values, { setSubmitting, resetForm }) => {
    try {
      await addEventFeedback(eventId, values);
      resetForm();
    } catch (error) {
      console.error("Error adding event:", error);
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <Formik
      initialValues={{
        feedback: "",
      }}
      onSubmit={onSubmit}
    >
      {({ isSubmitting, errors, touched }) => (
        <Form>
          <FormBox>
            <FormTextFieldBox>
              <Field
                label="Feedback"
                as={TextField}
                placeholder="Feedback"
                name="feedback"
                type="text"
              />
            </FormTextFieldBox>
            <FormActionButtonBox>
              <Button type="submit" disabled={isSubmitting}>
                Add
              </Button>
            </FormActionButtonBox>
          </FormBox>
        </Form>
      )}
    </Formik>
  );
}
export default EventFeedbackForm;
