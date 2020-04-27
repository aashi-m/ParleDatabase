package com.amazonaws.samples;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDB;
//import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.CreateTableResult;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;

public class Main {
	
	public static void main(String[] args)throws Exception
	{
		ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
		try {
			credentialsProvider.getCredentials();
		}
		catch(Exception e){
			throw new AmazonClientException(
				"Cannot load the credentials from the credential profiles file", e);
				
			}
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
				.withCredentials(credentialsProvider)
				.withRegion("us-west-1")
				.build();
		DynamoDB dynamoDB = new DynamoDB(client);
		
		Table data = dynamoDB.getTable("AashiTest");
		
		GetItemSpec spec = new GetItemSpec().withPrimaryKey("First","j", "Last", "S");
		
		try
		{
			System.out.println("Attempting to read the item: ");
			Item found = data.getItem(spec);
			System.out.println("GetItem succeeded: " + found);
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
