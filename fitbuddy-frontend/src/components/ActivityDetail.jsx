import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router'
import { getActivityDetail } from '../services/api';
import { Box, Card, CardContent, Divider, Typography } from '@mui/material';

const ActivityDetail = () => {

  const { id } = useParams();
  const [activity, setActivity] = useState([]);
  const [recommendation, setRecommendation] = useState([]);

  const fetchActivityDetail = async () => {
      try {
        const response = await getActivityDetail(id);
        setActivity(response.data);
        setRecommendation(response.data.recommendation);
      } catch (error) {
        console.error(error);
      }
    }

  useEffect(() => {
    fetchActivityDetail();
  }, [id]);

  if (!activity) {
    return <Typography>Loading...</Typography>
  }

  return (
    <Box sx={{ maxwidth: 800, mx: 'auto', p: 2 }}>

      <Card sx={{ mb: 2 }}>
        <CardContent>
          <Typography variant="h5" gutterBottom>Activity Details</Typography>
          <Typography>Type : {activity.activityType}</Typography>
          <Typography>Date : {new Date(activity.createdAt).toLocaleString()}</Typography>
        </CardContent>
      </Card>

      {
        recommendation && (
          <Card>
            <CardContent>

              <Typography variant="h5" gutterBottom>AI Recommendation</Typography>
              <Typography variant="h6" gutterBottom>Analysis</Typography>
              <Typography paragraph>{activity.recommendation}</Typography>

              <Divider sx={{ my: 2 }}></Divider>

              <Typography variant="h5">Improvements</Typography>
              {activity?.improvements?.map((improvement, index) => (
                <Typography key={index}>• {improvement}</Typography>
              ))}

              <Divider sx={{ my: 2 }}></Divider>

              <Typography variant="h5">Suggestions</Typography>
              {activity?.suggestions?.map((suggestion, index) => (
                <Typography key={index}>• {suggestion}</Typography>
              ))}

              <Divider sx={{ my: 2 }}></Divider>

              <Typography variant="h5">Safety Guidelines</Typography>
              {activity?.safety?.map((safety, index) => (
                <Typography key={index}>• {safety}</Typography>
              ))}

            </CardContent>
          </Card>
        )
      }

    </Box>
  )
}

export default ActivityDetail
