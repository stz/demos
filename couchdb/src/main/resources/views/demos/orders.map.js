function(doc) { 
   if (doc.doc_type == "Order") {
	   emit(doc._id, doc);
   }
}