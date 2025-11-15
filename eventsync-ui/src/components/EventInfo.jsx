import { Form, Field, Formik } from "formik";
import {
  FormActionButtonBox,
  FormBox,
  FormTextFieldBox,
} from "../styles/StyledComponents";
import { Button, TextField } from "@mui/material";

function EventForm() {
  const onSubmit = (values, { setSubmitting }) => {
    console.log(values);
    setSubmitting(false);
  };

  return (
    <Formik
      initialValues={{
        title: "",
        description: "",
      }}
      onSubmit={onSubmit}
    >
      {({ isSubmitting, errors, touched }) => (
        <Form>
          <FormBox>
            <FormTextFieldBox>
              <Field
                label="Title"
                as={TextField}
                placeholder="Title"
                name="title"
                type="text"
              />
              <Field
                label="Description"
                as={TextField}
                name="description"
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
export default EventForm;
